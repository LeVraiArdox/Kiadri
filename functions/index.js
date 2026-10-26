const { onDocumentCreated, onDocumentUpdated, onDocumentDeleted } = require("firebase-functions/v2/firestore");
const admin = require("firebase-admin");
const logger = require("firebase-functions/logger");

admin.initializeApp();

/**
 * Triggered when a new task is added.
 * Sends a notification to all users EXCEPT the creator.
 */
exports.notifyOnTaskAdded = onDocumentCreated("tasks/{taskId}", async (event) => {
    const taskData = event.data.data();
    if (!taskData) return;

    const title = taskData.title || "Nouvelle activité ajoutée !";
    const createdBy = taskData.createdBy;

    // Fetch all users
    const usersSnapshot = await admin.firestore().collection("users").get();

    const tokens = [];
    usersSnapshot.forEach(doc => {
        const userData = doc.data();
        if (userData.fcmToken && doc.id !== createdBy) {
            tokens.push(userData.fcmToken);
        }
    });

    if (tokens.length === 0) {
        logger.info("No tokens found to send new task notification.");
        return;
    }

    const payload = {
        notification: {
            title: "Nouvelle idée au programme !",
            body: `On a ajouté : ${title}`,
        }
    };

    try {
        const response = await admin.messaging().sendEachForMulticast({
            tokens: tokens,
            notification: payload.notification
        });
        logger.info(`Successfully sent ${response.successCount} messages on add.`);
    } catch (error) {
        logger.error("Error sending messages:", error);
    }
});

/**
 * Triggered when a task is updated (validated with photo).
 */
exports.notifyOnTaskValidated = onDocumentUpdated("tasks/{taskId}", async (event) => {
    const beforeData = event.data.before.data();
    const afterData = event.data.after.data();

    if (!beforeData || !afterData) return;

    // We only care if the task went from NOT completed to COMPLETED
    if (beforeData.completed === false && afterData.completed === true) {
        const title = afterData.title || "Activité";
        const completedBy = afterData.completedBy;

        const usersSnapshot = await admin.firestore().collection("users").get();

        const tokens = [];
        usersSnapshot.forEach(doc => {
            const userData = doc.data();
            if (userData.fcmToken && doc.id !== completedBy) {
                tokens.push(userData.fcmToken);
            }
        });

        if (tokens.length === 0) {
            logger.info("No tokens found to send validated task notification.");
            return;
        }

        const payload = {
            notification: {
                title: "Activité validée ! 🎉",
                body: `La preuve pour "${title}" a été ajoutée !`,
            }
        };

        try {
            const response = await admin.messaging().sendEachForMulticast({
                tokens: tokens,
                notification: payload.notification
            });
            logger.info(`Successfully sent ${response.successCount} messages on update.`);
        } catch (error) {
            logger.error("Error sending messages:", error);
        }
    }
});

/**
 * Triggered when a task is deleted.
 */
exports.notifyOnTaskDeleted = onDocumentDeleted("tasks/{taskId}", async (event) => {
    const taskData = event.data.data();
    if (!taskData) return;

    // Ignore completed tasks being deleted from history
    if (taskData.completed) return;

    const title = taskData.title || "Une activité";

    const usersSnapshot = await admin.firestore().collection("users").get();

    const tokens = [];
    usersSnapshot.forEach(doc => {
        const userData = doc.data();
        if (userData.fcmToken) {
            tokens.push(userData.fcmToken);
        }
    });

    if (tokens.length === 0) {
        logger.info("No tokens found to send deleted task notification.");
        return;
    }

    const payload = {
        notification: {
            title: "Activité supprimée 🗑️",
            body: `L'activité "${title}" a été retirée du programme.`,
        }
    };

    try {
        const response = await admin.messaging().sendEachForMulticast({
            tokens: tokens,
            notification: payload.notification
        });
        logger.info(`Successfully sent ${response.successCount} messages on delete.`);
    } catch (error) {
        logger.error("Error sending messages:", error);
    }
});