import Plugin.Plugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

public class Source {
    private static ArrayList<Plugin> plugins = new ArrayList<Plugin>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so nguyen thu nhat: ");
        int a = scanner.nextInt();
        System.out.println("Nhap so nguyen thu hai: ");
        int b = scanner.nextInt();
        initPlugins();
        for (Plugin plugin : plugins)
            System.out.println(plugin.getNameFunc() + " (" + a + "; " + b + ") : " + plugin.execute(a, b));
    }

    private static void initPlugins() {
        plugins.add(new GetMinTwoInteger());
        plugins.add(new GetMaxTwoInteger());
        addOtherPlugins();
    }

    private static void addOtherPlugins() {
        ArrayList<Class> classes = new ArrayList<>();
        ClassLoader classLoader = createClassLoader();
        if (classLoader != null)
        {
            File pluginsDirectory = new File("plugin1\\plugins\\");
            String []classNames = pluginsDirectory.list();
            for (String className : classNames)
                if (className.endsWith(".class"))
                {
                    String name = "plugins." + className.substring(0, className.length() - 6);
                    try{
                        Class cls = classLoader.loadClass(name);
                        classes.add(cls);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
        for (Class cls : classes)
        {
            try {
                Constructor<Plugin> constructor = cls.getConstructor();
                plugins.add(constructor.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ClassLoader createClassLoader() {
        File otherPluginDirectory = new File("plugin1\\");
        ClassLoader classLoader = null;
        try{
            URL[] urls = new URL[1];
            urls[0] = otherPluginDirectory.toURI().toURL();
            classLoader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classLoader;
    }
}
