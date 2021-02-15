package category.javabasic.multiThreads;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/web-crawler-multithreaded/discuss/699006/Java-BlockingQueue-%2B-ExecutorService
 *
 * Use main thread to take URLs from queue, and submit tasks into a thread pool. Threads in thread pool perform I/O and add URLs back to the queue.
 * The program exits when queue is empty and all tasks submitted to thread pool are completed.
 * BlockingQueue is thread-safe.
 * Other objects are only modified by the main thread, so no synchronization is needed.
 *
 * Created by brianzhang on 2/15/21.
 */
public class WebCrawler {
    public static void main(String[] args) {
        System.out.println("http://leetcode.com/problems".split("/")[2]);

    }

    ExecutorService es;
    public List<String> crawl(String startUrl, HtmlParser htmlParser)  {
        es = Executors.newFixedThreadPool( 10, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        Set<String> visited = new HashSet();
        BlockingQueue<Future<List<String>>> q = new LinkedBlockingQueue<>();
        String url = startUrl.split("/")[2];
        q.add(es.submit(() -> htmlParser.getUrls(startUrl)));
        visited.add(startUrl);
        try {
            while(true) {
                if (q.isEmpty()) break;

                List<String> urls = q.poll().get().stream().filter(val -> val.contains(url)).collect(Collectors.toList());

                for (String u : urls) {
                    if (visited.contains(u)) continue;
                    visited.add(u);
                    q.add(es.submit(() -> htmlParser.getUrls(u)));
                }
            }
        }
        catch (InterruptedException | ExecutionException e) {

        }
        return new ArrayList<>(visited);
    }

}

// Mocked HtmlParser
class HtmlParser {
    public List<String> getUrls(String startUrl){
        return Collections.EMPTY_LIST;
    }
}