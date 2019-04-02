import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromFile {
    private String ip;
    private String port;
    private String databaseUser;
    private String databaseUserPassword;
    private String databaseName;
    private String bindName;
    private String registryPort;

    public void readFile(String filename) throws IOException {
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        setIp(strLine = br.readLine());
        setPort(strLine = br.readLine());
        setDatabaseUser(strLine = br.readLine());
        setDatabaseUserPassword(strLine = br.readLine());
        setDatabaseName(strLine=br.readLine());
        setBindName(strLine = br.readLine());
        setRegistryPort(strLine = br.readLine());


    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public void setRegistryPort(String registryPort) {
        this.registryPort = registryPort;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public void setDatabaseUserPassword(String databaseUserPassword) {
        this.databaseUserPassword = databaseUserPassword;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setBindName(String bindName) {
        this.bindName = bindName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getDatabaseUserPassword() {
        return databaseUserPassword;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getBindName() {
        return bindName;
    }

    public String getRegistryPort() {
        return registryPort;
    }
}
