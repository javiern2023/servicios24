package FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FtpCliente {

    // Método para subir un archivo al servidor FTP
    public boolean uploadFile(String server, int port, String username, String password, String localFilePath, String remoteFilePath) {
        FTPClient ftpClient = new FTPClient();
        boolean success = false;
        
        try {
            // Conectar al servidor FTP
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            
            // Cambiar al modo binario para subir el archivo
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            // Subir el archivo
            try (FileInputStream inputStream = new FileInputStream(new File(localFilePath))) {
                success = ftpClient.storeFile(remoteFilePath, inputStream);
            }
            
            // Cerrar la conexión
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return success;
    }

    // Método para descargar un archivo desde el servidor FTP
    public boolean downloadFile(String server, int port, String username, String password, String remoteFilePath, String localFilePath) {
        FTPClient ftpClient = new FTPClient();
        boolean success = false;
        
        try {
            // Conectar al servidor FTP
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            
            // Cambiar al modo binario para descargar el archivo
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            // Descargar el archivo
            try (FileOutputStream outputStream = new FileOutputStream(new File(localFilePath))) {
                success = ftpClient.retrieveFile(remoteFilePath, outputStream);
            }
            
            // Cerrar la conexión
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return success;
    }

    public static void main(String[] args) {
        String server = "ftp.dlptest.com";  // Cambia esto por la dirección de tu servidor FTP
        int port = 21;  // Puerto FTP por defecto
        String username = "dlpuser";  // Cambia esto por tu nombre de usuario FTP
        String password = "rNrKYTX9g7z3RgJRmxWuGHbeu";  // Cambia esto por tu contraseña FTP

        // Archivos de ejemplo (ajusta las rutas según tus necesidades)
        String localFilePath = "C:/path/to/local/file.txt";  // Archivo local que se va a subir
        String remoteFilePath = "/remote/file.txt";  // Ruta en el servidor FTP donde se almacenará el archivo
        String downloadFilePath = "C:/path/to/local/downloaded_file.txt";  // Ruta local donde se descargará el archivo

        FtpCliente ftpClientExample = new FtpCliente();

        // Subir el archivo
        boolean uploadSuccess = ftpClientExample.uploadFile(server, port, username, password, localFilePath, remoteFilePath);
        if (uploadSuccess) {
            System.out.println("Archivo subido exitosamente.");

            // Descargar el archivo en otra ubicación
            boolean downloadSuccess = ftpClientExample.downloadFile(server, port, username, password, remoteFilePath, downloadFilePath);
            if (downloadSuccess) {
                System.out.println("Archivo descargado exitosamente.");
            } else {
                System.out.println("Error al descargar el archivo.");
            }
        } else {
            System.out.println("Error al subir el archivo.");
        }
    }
}

