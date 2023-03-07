package springEx.springEx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Proxy extends LogTest{
    private LogTest logTest;
    public Proxy(LogTest logTest) {
        this.logTest = logTest;
    }
    @Override
    public void printS() {
        log.info("asdadsada");
        logTest.printS();
    }
}
