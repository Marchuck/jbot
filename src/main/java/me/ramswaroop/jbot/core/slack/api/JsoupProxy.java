package me.ramswaroop.jbot.core.slack.api;

/**
 * @author Lukasz Marczak
 * @since 8/31/16
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author Lukasz Marczak
 * @since 26.05.16.
 */
public class JsoupProxy {
    private static class Log {
        static void d(String... s) {

        }

        static void e(String... s) {

        }
    }

    public static Observable<Document> getJsoupDocument(final String url) {
        return Observable.create(new Observable.OnSubscribe<Document>() {
            @Override
            public void call(Subscriber<? super Document> subscriber) {
                Document document = null;
                try {
                    Log.d("JsoupProxy", "calling url = " + url);
                    document = Jsoup.connect(url).get();
                } catch (Exception ignored) {
                    Log.e("JsoupProxy", "getDocument: " + ignored.getMessage());
                    subscriber.onError(ignored);
                }
                subscriber.onNext(document);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    public static void printElement(String TAG, Element e) {
        Log.d(TAG, "element: html: " + e.html() + ", text: " + e.text() + ", val: " + e.val() + ", data: "
                + e.data() + ", id: " + e.id() + ", nodeName: " + e.nodeName() + ", tag: " + e.tag()
                + ", tagName: " + e.tagName() + ", \n" + e.outerHtml());
    }

    public static void printElements(String TAG, Elements elements) {
        for (Element el : elements) printElement(TAG, el);
    }

}
