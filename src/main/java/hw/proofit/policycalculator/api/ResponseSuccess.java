package hw.proofit.policycalculator.api;

public class ResponseSuccess implements Response {
    private String premium;

    public ResponseSuccess(String premium) {
        this.premium = premium;
    }

    public String getPremium() {
        return premium;
    }
}
