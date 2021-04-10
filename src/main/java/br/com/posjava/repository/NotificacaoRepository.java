package br.com.posjava.repository;

import br.com.posjava.model.Notificacao;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@ApplicationScoped
public class NotificacaoRepository implements PanacheRepositoryBase<Notificacao, UUID> {
}
