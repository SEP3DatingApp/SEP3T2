package Shared;

public class Request {
    public String Type;
    public Object Args;

    public Request(String Type, Object Args)
    {
        this.Type = Type;
        this.Args = Args;
    }



    @Override
    public String toString()
    {
        return "Request{" + "Type=" + Type + ", Args=" + Args + '}';
    }
}
