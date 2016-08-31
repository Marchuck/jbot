package me.ramswaroop.jbot.core.slack.api;

/**
 * @author Lukasz Marczak
 * @since 8/31/16
 */
public class SinglePasta implements CharSequence {
    private String pastaContent;

    public SinglePasta(String s) {
        pastaContent = s;
    }

    @Override
    public int length() {
        return pastaContent.length();
    }

    @Override
    public char charAt(int index) {
        return pastaContent.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return pastaContent.subSequence(start,end);
    }
}
