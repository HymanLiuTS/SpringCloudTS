package cn.codenest.zuulserver.utils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.*;
import java.security.KeyStore;
import java.util.Map;

public class HttpsPost {
    static class MyAuthenticator extends Authenticator {

        public MyAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        String username;
        String password;

        public PasswordAuthentication getPasswordAuthentication() {

            return (new PasswordAuthentication(username, password.toCharArray()));
        }
    }

    // static Proxy proxy = new Proxy(Proxy.Type.HTTP, new
    // InetSocketAddress("192.168.5.64", 9998)); // TODO Delete

    /**
     * 获得KeyStore.
     *
     * @param keyStorePath 密钥库路径
     * @param password     密码
     * @return 密钥库
     * @throws Exception
     */
    public static KeyStore getKeyStore(String password, String keyStorePath) throws Exception {
        // 实例化密钥库
        KeyStore ks = KeyStore.getInstance("JKS");
        // 获得密钥库文件流
        FileInputStream is = new FileInputStream(keyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
        // 关闭密钥库文件流
        is.close();
        return ks;
    }

    /**
     * 获得SSLSocketFactory.
     *
     * @param password       密码
     * @param keyStorePath   密钥库路径
     * @param trustStorePath 信任库路径
     * @return SSLSocketFactory
     * @throws Exception
     */
    public static SSLContext getSSLContext(String password, String keyStorePath, String trustStorePath)
            throws Exception {
        // 实例化密钥库
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        // 获得密钥库
        KeyStore keyStore = getKeyStore(password, keyStorePath);
        // 初始化密钥工厂
        keyManagerFactory.init(keyStore, password.toCharArray());

        // 实例化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        // 获得信任库
        KeyStore trustStore = getKeyStore(password, trustStorePath);
        // 初始化信任库
        trustManagerFactory.init(trustStore);
        // 实例化SSL上下文
        SSLContext ctx = SSLContext.getInstance("TLS");
        // 初始化SSL上下文
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        // 获得SSLSocketFactory
        return ctx;
    }

    /**
     * 初始化HttpsURLConnection.
     *
     * @param password       密码
     * @param keyStorePath   密钥库路径
     * @param trustStorePath 信任库路径
     * @throws Exception
     */
    public static void initHttpsURLConnection(String password, String keyStorePath, String trustStorePath)
            throws Exception {
        /*
         * // 声明SSL上下文 SSLContext sslContext = null; // 实例化主机名验证接口
         * HostnameVerifier hnv = new MyHostnameVerifier(); try { sslContext =
         * getSSLContext(password, keyStorePath, trustStorePath); } catch
         * (GeneralSecurityException e) { e.printStackTrace(); } if (sslContext
         * != null) { HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
         * .getSocketFactory()); }
         * HttpsURLConnection.setDefaultHostnameVerifier(hnv);
         */
    }

    /**
     * 发送请求.
     *
     * @param httpsUrl 请求的地址
     * @param reqParam 请求的参数数据
     */
    public static String post(String httpsUrl, String reqParam) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) (new URL(httpsUrl)).openConnection(/* proxy */);
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            /**
             * urlCon.setRequestProperty("Content-Length",
             * String.valueOf(xmlStr.getBytes().length));
             * urlCon.setUseCaches(false); //设置为gbk可以解决服务器接收时读取的数据中文乱码问题
             * urlCon.getOutputStream().write(xmlStr.getBytes("gbk"));
             * urlCon.getOutputStream().flush();
             * urlCon.getOutputStream().close();
             */
            urlCon.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            urlCon.setRequestProperty("Acccept", "application/json;charset=utf-8");
            urlCon.setRequestMethod("POST");
            urlCon.connect();
            OutputStreamWriter requestSender = new OutputStreamWriter(urlCon.getOutputStream(), "UTF-8");

            requestSender.write(reqParam);
            requestSender.flush();
            requestSender.close();
            //
            // BufferedReader in = null;
            // if(urlCon.getResponseCode() == HttpURLConnection.HTTP_OK ||
            // urlCon.getResponseCode() == HttpURLConnection.HTTP_CREATED ||
            // urlCon.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {
            // in = new BufferedReader(new
            // InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            // } else {
            // in = new BufferedReader(new
            // InputStreamReader(urlCon.getErrorStream(), "UTF-8"));
            // }
            //
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            String output = "";
            String line;
            while ((line = in.readLine()) != null) {
                output += line;
            }
            in.close();
            return output;
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 发送请求.
     *
     * @param httpsUrl 请求的地址
     * @param reqParam 请求的参数数据
     */
    public static String post(String httpsUrl, String reqParam, Map<String, String> header) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) (new URL(httpsUrl)).openConnection(/* proxy */);
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            /**
             * urlCon.setRequestProperty("Content-Length",
             * String.valueOf(xmlStr.getBytes().length));
             * urlCon.setUseCaches(false); //设置为gbk可以解决服务器接收时读取的数据中文乱码问题
             * urlCon.getOutputStream().write(xmlStr.getBytes("gbk"));
             * urlCon.getOutputStream().flush();
             * urlCon.getOutputStream().close();
             */
            urlCon.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            urlCon.setRequestProperty("Acccept", "application/json;charset=utf-8");
            for (String key : header.keySet()) {
                urlCon.setRequestProperty(key, header.get(key));
            }
            urlCon.setRequestMethod("POST");
            urlCon.connect();
            OutputStreamWriter requestSender = new OutputStreamWriter(urlCon.getOutputStream(), "UTF-8");

            requestSender.write(reqParam);
            requestSender.flush();
            requestSender.close();
            //
            // BufferedReader in = null;
            // if(urlCon.getResponseCode() == HttpURLConnection.HTTP_OK ||
            // urlCon.getResponseCode() == HttpURLConnection.HTTP_CREATED ||
            // urlCon.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {
            // in = new BufferedReader(new
            // InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            // } else {
            // in = new BufferedReader(new
            // InputStreamReader(urlCon.getErrorStream(), "UTF-8"));
            // }
            //
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            String output = "";
            String line;
            while ((line = in.readLine()) != null) {
                output += line;
            }
            in.close();
            return output;
        } catch (Throwable e) {
        }
        return null;
    }

    public static String post(String httpsUrl, String reqParam, String username, String password) {
        Authenticator.setDefault(new MyAuthenticator(username, password));
        return post(httpsUrl, reqParam);
    }

    public static String get(String httpsUrl, Map<String, String> header) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) (new URL(httpsUrl)).openConnection(/* proxy */);
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("GET");
            urlCon.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            urlCon.setRequestProperty("Acccept", "application/json;charset=utf-8");
            for (String key : header.keySet()) {
                urlCon.setRequestProperty(key, header.get(key));
            }
            urlCon.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            String output = "";
            String line;
            while ((line = in.readLine()) != null) {
                output += line;
            }
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String httpsUrl) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) (new URL(httpsUrl)).openConnection(/* proxy */);
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("GET");
            urlCon.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            urlCon.setRequestProperty("Acccept", "application/json;charset=utf-8");

            urlCon.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            String output = "";
            String line;
            while ((line = in.readLine()) != null) {
                output += line;
            }
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String httpsUrl, String username, String password) {
        Authenticator.setDefault(new MyAuthenticator(username, password));
        return get(httpsUrl);
    }

    public static String delete(String httpsUrl) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) (new URL(httpsUrl)).openConnection(/* proxy */);
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("DELETE");

            urlCon.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            urlCon.setRequestProperty("Acccept", "application/json;charset=utf-8");

            urlCon.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            String output = "";
            String line;
            while ((line = in.readLine()) != null) {
                output += line;
            }
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String delete(String httpsUrl, String username, String password) {
        Authenticator.setDefault(new MyAuthenticator(username, password));
        return delete(httpsUrl);
    }
}
