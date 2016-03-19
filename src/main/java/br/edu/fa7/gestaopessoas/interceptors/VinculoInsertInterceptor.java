package br.edu.fa7.gestaopessoas.interceptors;

import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import br.edu.fa7.gestaopessoas.models.Departamento;
import br.edu.fa7.gestaopessoas.models.Vinculo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 19/03/16.
 */
public class VinculoInsertInterceptor implements PreInsertEventListener {

  @Override
  public boolean onPreInsert(PreInsertEvent preInsertEvent) {
    Object entity = preInsertEvent.getEntity();
    if(entity instanceof Vinculo){
      Vinculo vinculo = (Vinculo) entity;
      Departamento departamento = vinculo.getDepartamento();
      if(!departamento.getCargos().contains(  vinculo.getCargo() ) ){
        throw new IllegalStateException("Departamento (" + vinculo.getDepartamento() +") não comporta o cargo especificado (" + vinculo.getCargo() + ").");
      }
    }
    return false;
  }

}
