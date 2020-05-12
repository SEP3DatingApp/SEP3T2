package Shared;

public class Request {
    public String Type;
    public Object Args;

    public Request(String Type, Object Args)
    {
        this.Type = Type;
        this.Args = Args;
    }
    public String getType() {
        return Type;
    }
    public Object getArgs() {
        return Args;
    }
}
