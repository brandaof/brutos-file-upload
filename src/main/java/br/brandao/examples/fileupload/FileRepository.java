package br.brandao.examples.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class FileRepository {

	public static final String FILE_PATTERN = "^([a-z_A-Z0-9]+)\\.([a-z_A-Z0-9]{1,20})$";
	
    private File root;
    
    @Inject
	public FileRepository(
			@Named("fileRepositoryPath")
			String fileRepositoryPath){
		this.root = new File(fileRepositoryPath);
	}
    
    public String[] getFiles(){
        return this.root.list();
    }
    
	public File getFile(String name){
		
		if(name == null || !name.matches(FILE_PATTERN)){
			return null;
		}
		
		File f = new File(this.root, name);
		return f.exists()? f : null;
	}
	
	public File addFile(File file, String name){
		try{
			if(name == null || !name.matches(FILE_PATTERN)){
				return null;
			}
			
			File dest = new File(this.root, name);
			this.cp(file, dest);
			return dest;
		}
		catch(Throwable e){
			return null;
		}
	}

	public File deleteFile(String name){
		
		if(name == null || !name.matches(FILE_PATTERN)){
			return null;
		}
		
		File f = new File(this.root, name);
		
		if(f.exists()){
			f.delete();
			return f;
		}
		
		return null;
	}
	
	private void cp(File origin, File dest) throws IOException{
		
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try{
			dest.createNewFile();
			
			in = new FileInputStream(origin);
			out = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int l;
			
			while((l = in.read(buf)) != -1){
				out.write(buf, 0, l);
			}
			
			out.flush();
		}
		finally{
			if(in != null){
				in.close();
			}
			
			if(out != null){
				out.close();
			}
		}
		
	}
	
}
