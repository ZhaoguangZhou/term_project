import domain.Account;
import service.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;



public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void init() throws IOException{
        accountService=new AccountService();
    }

    @Test
    public void testFindAll(){
        accountService.findAll();
    }

    @Test
    public void testDeleteByPrimaryKey(){
        accountService.deleteByPrimaryKey("1");
    }

    @Test
    public void testInsert(){
        Account account = new Account("3","Elysia",1000,null,null);
        accountService.insert(account);
    }

    @Test
    public void testSelectByPrimaryKey(){
        accountService.selectByPrimaryKey("2");
    }

    @Test
    public void testUpdateByPrimaryKey(){
        Account account = new Account("2","Tom",10000,null,null);
        accountService.updateByPrimaryKey(account);
    }

    @Test
    public void testTransfer(){
        accountService.transfer("2","3",1000);
    }

}
