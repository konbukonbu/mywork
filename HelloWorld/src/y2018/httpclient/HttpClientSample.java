package y2018.httpclient;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Http通信のサンプル
 *
 * @see https://itsakura.com/java-httpclient
 * @see https://qiita.com/e-taka/items/1b15b59fdf7420ef074d
 * @see http://sc.seeeko.com/archives/cat_125403.html
 *
 */
public class HttpClientSample {
	public static void main(String[] args) throws Exception {
		connect();
	}

	private static void connect() throws Exception {
		Charset charset = StandardCharsets.UTF_8;

		// CloseableHttpClient httpclient = HttpClients.createDefault();

		SSLContext sslContext = SSLContexts.custom().useTLS() // Only this
																// turned out to
																// be not enough
				.build();
		SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,
				new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient customClient = HttpClients.custom().setSSLSocketFactory(sf).build();

		String url = "https://github.com/konbukonbu/mywork";
		HttpGet request = new HttpGet(url);

		System.out.println("requestの実行　「" + request.getRequestLine() + "」");
		// requestの実行 「GET http://httpbin.org/get HTTP/1.1」

		CloseableHttpResponse response = null;

		// response = httpclient.execute(request);
		response = customClient.execute(request);

		int status = response.getStatusLine().getStatusCode();
		System.out.println("HTTPステータス:" + status);
		// HTTPステータス:200

		if (status == HttpStatus.SC_OK) {
			String responseData = EntityUtils.toString(response.getEntity(), charset);
			System.out.println(responseData);
			// 取得したデータが表示される
		}
	}
}
