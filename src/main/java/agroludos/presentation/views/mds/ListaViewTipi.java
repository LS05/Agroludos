package agroludos.presentation.views.mds;

import java.util.List;

import agroludos.to.TipiAgroludosTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * gestisce la listview dei tipi nella view principale del manager di sistema
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ListaViewTipi extends ListView<String>{

	private ObservableList<String> names = FXCollections.observableArrayList();

	ListaViewTipi(List<TipiAgroludosTO> mainList){

		for(TipiAgroludosTO item : mainList){
			this.names.add(item.getNome());
		}

		this.setItems(names);

	}

	void addItem(TipiAgroludosTO tipo){
		this.names.add(tipo.getNome());
	}
}