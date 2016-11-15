package br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils;

import java.util.ResourceBundle;

/**
 * Classe singleton para carregar configurações e metodos auxiliares
 * 
 * @author Andrey
 * @since 15/11/2016
 */
public class MyUtils {
    private static enum Singleton {
        INSTANCE;               

        private ResourceBundle bundle;

        private Singleton() {
            System.out.println("Lendo arquivo de configuração: " + 
            System.currentTimeMillis());

            bundle = ResourceBundle.getBundle("config");
        }

        private ResourceBundle getResourceBundle() {
            return bundle;
        }

        private String getResourceAsString(String name) {
            return bundle.getString(name);
        }
    };

    private MyUtils() {}

    public static ResourceBundle getResourceBundle() {
        return Singleton.INSTANCE.getResourceBundle();
    }

    public static String getResourceAsString(String name) {
        return Singleton.INSTANCE.getResourceAsString(name);
    }

    public static String getPropertie(String key) {
        return Singleton.INSTANCE.getResourceAsString(key);
    }
	
}
