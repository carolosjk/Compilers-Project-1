class ParseException extends Exception{
    public ParseException(String msg) {
        super("Parse error! " + msg);
    }

    public ParseException() {
        super("Parse error!");
    }
}
