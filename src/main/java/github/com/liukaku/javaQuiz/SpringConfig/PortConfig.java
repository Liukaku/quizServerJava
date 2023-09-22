package github.com.liukaku.javaQuiz.SpringConfig;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class PortConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    public void customize(TomcatServletWebServerFactory  factory) {
        Dotenv dotenv = Dotenv.load();
        String port = dotenv.get("PORT");

        factory.setPort(Integer.parseInt(port));
        factory.setContextPath("");
    }

}
