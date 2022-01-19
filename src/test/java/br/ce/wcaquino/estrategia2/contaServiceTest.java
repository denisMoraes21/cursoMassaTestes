package br.ce.wcaquino.estrategia2;

import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class contaServiceTest {

    static Faker faker = new Faker();
    ContaService service = new ContaService();
    UsuarioService userService = new UsuarioService();
    private static Usuario usuarioGlobal;
    private Conta contaTeste;

    @BeforeClass //Apenas uma vez antes de todos os testes
    public static void criarUsuario(){
        usuarioGlobal = new Usuario(faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
    }

    @Before
    public void inserirConta() throws Exception{
        Usuario usuarioSalvo = userService.salvar(usuarioGlobal);
        Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
        contaTeste =  service.salvar(conta);
    }

    //Entrada: 0
    //Saída: M1
    @Test
    public void testInserir() throws Exception{
        Usuario user = new Usuario(faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
        Usuario usuarioSalvo = userService.salvar(user);
        Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
        Conta contaSalva = service.salvar(conta);
        Assert.assertNotNull(contaSalva.getId());
        service.printAll();
        //excluirRegistro(contaSalva);
    }

    //Entrada: M2
    //Saída: M2
    @Test
    public void testConsultar() throws Exception{
        Conta contaBuscada = service.findById(contaTeste.getId());
        Assert.assertEquals(contaTeste.getNome(), contaBuscada.getNome());
        //excluirRegistro(contaBuscada);
    }

    //Entrada: M3
    //Saída: M3'
    @Test
    public void testAlterar() throws Exception{
        String novoNome = faker.ancient().god();
        contaTeste.setNome(novoNome);
        Conta contaAlterada = service.salvar(contaTeste);
        Assert.assertEquals(novoNome, contaAlterada.getNome());
        service.printAll();
        //excluirRegistro(contaAlterada);
    }

    //Entrada: M4
    //Saída: 0
    @Test
    public void testExcluir() throws Exception{
        service.printAll();
        service.delete(contaTeste);
        Conta contaBuscada = service.findById(contaTeste.getId());
        Assert.assertNull(contaBuscada);
        service.printAll();
    }

    private void excluirRegistro(Conta registro) throws Exception{
        service.delete(registro);
    }


}
