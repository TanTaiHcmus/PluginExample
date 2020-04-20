import Plugin.Plugin;

public class GetMaxTwoInteger implements Plugin {


    @Override
    public String getNameFunc() {
        return "Get Max Two Integer";
    }

    @Override
    public Object execute(Object o, Object o1) {
        int a, b;
        try{
            a = (int) o;
            b = (int) o1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        if (a >= b) return a;
        else return b;
    }
}
