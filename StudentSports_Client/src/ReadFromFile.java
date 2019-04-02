import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


    public class ReadFromFile {
        private String ip;
        private String port;
        private String bindedName;

        public void readFile(String filename) throws IOException {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            setIp(strLine = br.readLine());
            setPort(strLine = br.readLine());
            setBindedName(strLine = br.readLine());

        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public void setBindedName(String bindedName) {
            this.bindedName = bindedName;
        }

        public String getIp() {
            return ip;
        }

        public String getPort() {
            return port;
        }

        public String getBindedName() {
            return bindedName;
        }
    }


