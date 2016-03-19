package br.edu.fa7.gestaopessoas.interceptors;

import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import br.edu.fa7.gestaopessoas.models.Departamento;
import br.edu.fa7.gestaopessoas.models.Vinculo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.event.spi.*;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 19/03/16.
 */
public class PreInsertUpdateVinculoInterceptor implements PreInsertEventListener, PreUpdateEventListener {

  @Override
  public boolean onPreInsert(PreInsertEvent preInsertEvent) {
    Object entity = preInsertEvent.getEntity();
    if(entity instanceof Vinculo){
      verificaDepartamentoCargoDoVinculo( (Vinculo) entity );
    }
    return false;
  }

  @Override
  public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
    Object entity = preUpdateEvent.getEntity();
    if(entity instanceof Vinculo){
      verificaDepartamentoCargoDoVinculo( (Vinculo) entity );
    }
    return false;
  }

  protected void verificaDepartamentoCargoDoVinculo(Vinculo vinculo){
    Departamento departamento = vinculo.getDepartamento();
    if(!departamento.getCargos().contains(  vinculo.getCargo() ) ){
      throw new IllegalStateException("Departamento (" + vinculo.getDepartamento() +") não comporta o cargo especificado (" + vinculo.getCargo() + ").");
    }
  }

}
