package net.lux.core;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class LuxClassLoader extends URLClassLoader {
    private final List<URL> urls = new ArrayList<>();

    public LuxClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    public void addModFolder(File folder) {
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".jar"));
            if (files != null) {
                for (File file : files) {
                    try {
                        URL url = file.toURI().toURL();
                        this.addURL(url);
                        urls.add(url);
                        System.out.println("[LuxLoader] Added to ClassPath: " + file.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void addModFile(File file) {
        try {
            this.addURL(file.toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void addURL(URL url) {
        super.addURL(url);
    }
}
