
package br.brandao.examples.fileupload.web;

import java.io.File;

import javax.inject.Inject;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.web.RequestMethodTypes;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.http.Download;
import org.brandao.brutos.web.http.UploadedFile;
import org.brandao.brutos.web.http.download.FileDownload;

import br.brandao.examples.fileupload.FileRepository;
/**
 *
 * @author Neto
 */
@Action(value="/files", view=@View("files/list"))
public class IndexController{

	@Transient
	@Inject
	private FileRepository repository;
	
	@Action("/")
    public void index(){
		WebFlowController.redirectTo("/files");
	}
    		
	@Action("/files")
	@RequestMethod(RequestMethodTypes.POST)
    public void addFile(
    		@Basic(bean="file")
    		UploadedFile[] file) {

		if(file != null){
			for(UploadedFile f: file){
				this.repository.addFile(f.getFile(), f.getFileName());
			}
		}
		
		WebFlowController.redirectTo("/files");
    }

	@Action("/files/delete/{fileName:[^\\s]+}")
	@RequestMethod(RequestMethodTypes.POST)
    public void delete(
    		@Basic(bean="fileName")
    		String fileName ){
		
		this.repository.deleteFile(fileName);
		WebFlowController.redirectTo("/files");
    }

	@Action("/files/{fileName:[^\\s]+}")
    public Download download(
    		@Basic(bean="fileName")
    		String fileName){
		
		File file = this.repository.getFile(fileName);
		
		if(file != null){
            return new FileDownload( file );
		}
		
		WebFlowController.redirectTo("/files");
		return null;
    }

	@Transient
    public String[] getFiles() {
		return this.repository.getFiles();
    }
}
