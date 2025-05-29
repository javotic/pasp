package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.google.common.io.ByteStreams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.io.BufferedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ProcesoEscalafonarioVigenteDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;

import com.jcraft.jsch.Session;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DescargaArchivosService;
import org.springframework.beans.factory.annotation.Value;

@Service
public class DescargaArchivosServiceImpl implements DescargaArchivosService {

        
        @Value("${files.ftp.host}")
	private String dsHost;
        
        @Value("${files.ftp.port}")
	private String dsPuerto;
                
        @Value("${files.ftp.user}")
	private String dsUser;
                        
        @Value("${files.ftp.pass}")
	private String dsPasswd;
                                
        @Value("${files.ftp.path}")
	private String dsPath;

        @Override
	public byte[] descargaGuiaEstudio(String idGuiaPuesto) throws BusException{
		byte[] consultas = null;
                BufferedInputStream bis;
                
                /*
                    String dsHost = "10.10.48.33";
                    Integer dsPuerto = 22;
                    String dsUser = "userprod";
                    String dsPasswd = "$P0rt91l$020";
        */
                    Session session;
                    Channel channel;
                    ChannelSftp sftpChannel;

                    //Conectamos con el FTP
                    JSch jsch = new JSch();
                    try {
                    
                    session = jsch.getSession(dsUser, dsHost, Integer.valueOf(dsPuerto));
                    session.setPassword(dsPasswd);
                    session.setConfig("PreferredAuthentications", "password");
                    session.setConfig("StrictHostKeyChecking", "no");
                
                    session.connect();
                    channel = session.openChannel("sftp");
                    channel.connect();
                    sftpChannel = (ChannelSftp) channel;   
 
                     sftpChannel.cd(dsPath);
                     bis = new BufferedInputStream(sftpChannel.get(idGuiaPuesto + ".pdf"));           
                     consultas = ByteStreams.toByteArray(bis);
      
		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
                
                sftpChannel.disconnect();
		channel.disconnect();
		session.disconnect();
                
		return consultas;
	}
        
        
        
        
}
