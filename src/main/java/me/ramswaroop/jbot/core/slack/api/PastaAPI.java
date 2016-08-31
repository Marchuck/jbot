package me.ramswaroop.jbot.core.slack.api;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * @author Lukasz Marczak
 * @since 8/31/16
 */
public class PastaAPI {


    String endpoint = "https://vichan.net/pasty/top150?token=qQLKf0hudrj8KRUTirdB5BCA&team_id=T0001";

    public rx.Observable<List<CharSequence>> latest() {
        return JsoupProxy.getJsoupDocument(endpoint)
                .flatMap(new Func1<Document, Observable<Element>>() {
                    @Override
                    public Observable<Element> call(Document document) {
                        Elements elements = document.getElementsByClass("quote_output");
                        return Observable.from(elements);
                    }
                })
                .map((Func1<Element, CharSequence>) element -> {
                    String content = element.text();
                    return new SinglePasta(content);
                }).toList();
    }

}
