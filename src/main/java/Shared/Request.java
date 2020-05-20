package Shared;

import org.json.JSONObject;

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
    } //GETALLUSERS,GETUSER,CREATEUSER,LOGIN
    public JSONObject getArgs() {
        return (JSONObject) Args;
    } //user

    @Override
    public String toString()
    {
        return "Request{" + "Type='" + Type + '\'' + ", Args=" + Args + '}';
    }

    public void setTypeToEmpty()
    {
        Type = "empty";
    }
}
