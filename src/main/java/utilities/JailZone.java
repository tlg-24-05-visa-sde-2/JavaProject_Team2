package utilities;

import components.Token;

import java.util.ArrayList;
import java.util.List;

public class JailZone {
    private List<Token> jailedTokens;

    public JailZone() {
        jailedTokens = new ArrayList<>();
    }

    public void addToken(Token token) {
        token.setInJail(true);
        jailedTokens.add(token);
    }

    public void removeToken(Token token) {
        token.setInJail(false);
        jailedTokens.remove(token);
    }

    public List<Token> getJailedTokens() {
        return jailedTokens;
    }
}