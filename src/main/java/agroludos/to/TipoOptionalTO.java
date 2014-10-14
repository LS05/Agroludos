package agroludos.to;

import java.util.List;

public interface TipoOptionalTO extends TipiAgroludosTO, AgroludosTO, Comparable<TipoOptionalTO>{

	public String getNome();

	public void setNome(String nome);

	Integer getId();

	List<OptionalTO> getAllOptionals();

	List<OptionalTO> getAllOptionalAttivi();
}