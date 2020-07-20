package JavaRede;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class lerArquivo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jta;

	public lerArquivo () {
		addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	//	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize (400,200);
		Container c = getContentPane();
		jta = new JTextArea ("",10,10);
		c.add (new JScrollPane(jta) );
		setVisible (true);
	}
	public void listar(String info) {
		jta.append(info + "\n");

	}
	
	/*public void lendo(String arq) {
		lerArquivo arqtxt = new lerArquivo();
	//	File fileName;
		//JFileChooser jfc = new JFileChooser ();
		//jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//int result = jfc.showOpenDialog(arqtxt);
		//if (result == JFileChooser.CANCEL_OPTION) return;
		//fileName = jfc.getSelectedFile();
		//if (fileName == null || fileName.getName().equals("")) {
			
		//}else {
		String msg = arq;
			try {
			//	arqtxt.ler (fileName.getName());
				
				arqtxt.ler(msg);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Não foi possivel abrir o arquivo " +msg,"Erro na leitura",JOptionPane.PLAIN_MESSAGE);
			}
		}
		
	//}
	
	private void ler (String nomArq) throws IOException  {
		BufferedReader br = new BufferedReader (new FileReader (nomArq));
		String lin = "";
		while ((lin = br.readLine()) != null) {
			jta.append(lin + "\n");
		}
		br.close();
	}*/
}

