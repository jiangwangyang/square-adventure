package com.github.jiangwangyang.square.adventure.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class PackageLoaderUtil {

    private PackageLoaderUtil() {
    }

    /**
     * @param pkg 包名，例如 "com.example.model"
     * @return 加载成功的类列表
     * 主动加载指定包名下所有类（默认递归子包）
     */
    public static List<Class<?>> load(String pkg) {
        return load(pkg, true, null, null);
    }

    /**
     * @param pkg          包名
     * @param recursive    是否递归子包
     * @param nameRegex    类名正则，保留匹配者；null 表示全保留
     * @param excludeRegex 类名正则，排除匹配者；null 表示不排除
     * @return 加载成功的类列表
     * 按需加载并过滤
     */
    public static List<Class<?>> load(String pkg,
                                      boolean recursive,
                                      String nameRegex,
                                      String excludeRegex) {
        String path = pkg.replace('.', '/');
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> result = new ArrayList<>();
        try {
            Enumeration<URL> resources = loader.getResources(path);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    loadFromDirectory(new File(url.toURI()), pkg, recursive, nameRegex, excludeRegex, result);
                } else if ("jar".equals(protocol)) {
                    loadFromJar(url, pkg, recursive, nameRegex, excludeRegex, result);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Scan package error: " + pkg, e);
        }
        return result;
    }

    /* ---------------- 内部实现 ---------------- */

    private static void loadFromDirectory(File dir,
                                          String pkg,
                                          boolean recursive,
                                          String nameRegex,
                                          String excludeRegex,
                                          List<Class<?>> out) {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
                if (recursive) {
                    loadFromDirectory(f, pkg + "." + f.getName(), recursive, nameRegex, excludeRegex, out);
                }
            } else if (f.getName().endsWith(".class")) {
                String className = pkg + '.' + f.getName().substring(0, f.getName().length() - 6);
                loadClass(className, nameRegex, excludeRegex, out);
            }
        }
    }

    private static void loadFromJar(URL jarUrl,
                                    String pkg,
                                    boolean recursive,
                                    String nameRegex,
                                    String excludeRegex,
                                    List<Class<?>> out) throws IOException {
        String jarPath = jarUrl.getPath();
        String jarFilePath = jarPath.substring(5, jarPath.indexOf('!'));
        try (JarFile jar = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.startsWith(pkg.replace('.', '/')) || entry.isDirectory()) {
                    continue;
                }
                if (!recursive && name.substring(pkg.length() + 1).contains("/")) {
                    continue;
                }
                if (name.endsWith(".class")) {
                    String className = name.replace('/', '.').substring(0, name.length() - 6);
                    loadClass(className, nameRegex, excludeRegex, out);
                }
            }
        }
    }

    private static void loadClass(String className,
                                  String nameRegex,
                                  String excludeRegex,
                                  List<Class<?>> out) {
        if (excludeRegex != null && className.matches(excludeRegex)) {
            return;
        }
        if (nameRegex != null && !className.matches(nameRegex)) {
            return;
        }
        try {
            Class<?> clazz = Class.forName(className);
            out.add(clazz);
        } catch (Throwable e) {
            // 统一收集，不中断；可换成日志
            System.err.println("Load class failed: " + className + " -> " + e.getMessage());
        }
    }
}
