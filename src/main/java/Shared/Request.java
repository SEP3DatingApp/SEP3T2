package Shared;

import org.json.JSONObject;

public class Request {
    private String Type;
    private JSONObject Args;

    public Request(String Type, JSONObject Args)
    {
        this.Type = Type;
        this.Args = Args;
    }
    public String getType() {
        return Type;
    } //GETALLUSERS,GETUSER,CREATEUSER,LOGIN
    public JSONObject getArgs() {
        return Args;
    } //user

    @Override
    public String toString()
    {
        return "Request{" + "Type='" + Type + '\'' + ", Args=" + Args + '}';
    }
}
