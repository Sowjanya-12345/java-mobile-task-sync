import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Task {
        final int id;
        final String title;
        final LocalDateTime createdAt;
        boolean synced;

        Task(int id, String title) {
            this.id = id;
            this.title = title;
            this.createdAt = LocalDateTime.now();
        }
    }

    public static void main(String[] args) {
        List<Task> offlineQueue = new ArrayList<>();
        offlineQueue.add(new Task(101, "Book GP appointment"));
        offlineQueue.add(new Task(102, "Submit weekly report"));
        offlineQueue.add(new Task(103, "Buy groceries"));

        System.out.println("=== Mobile Task Sync Simulator ===");
        System.out.println("Device is offline. Tasks stored locally: " + offlineQueue.size());

        boolean networkAvailable = true;
        if (networkAvailable) {
            syncTasks(offlineQueue);
        }

        long syncedCount = offlineQueue.stream().filter(task -> task.synced).count();
        System.out.println("Synced tasks: " + syncedCount + "/" + offlineQueue.size());
    }

    static void syncTasks(List<Task> tasks) {
        System.out.println("Network restored. Starting sync...");
        for (Task task : tasks) {
            System.out.printf("Uploading #%d - %s ... ", task.id, task.title);
            task.synced = true;
            System.out.println("DONE");
        }
    }
}
