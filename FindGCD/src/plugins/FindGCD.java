package plugins;

import Plugin.Plugin;

public class FindGCD implements Plugin {
    @Override
    public String getNameFunc() {
        return "Find GCD From 2 Integer";
    }

    public int GCD(int x, int y)
    {
        if (y == 0) return x;
        return GCD(y, x % y);
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
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        return GCD(a, b);
    }
}

