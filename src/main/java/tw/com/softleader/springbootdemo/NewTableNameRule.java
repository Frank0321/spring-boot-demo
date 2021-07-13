package tw.com.softleader.springbootdemo;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.util.Locale;

public class NewTableNameRule extends SpringPhysicalNamingStrategy {

    protected String tablePrefix(){
        return "sl_";
    }

    //table 前面需要加的前綴
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
        return new Identifier(tablePrefix() + identifier.getText(), identifier.isQuoted());
    }

    //table 後面需要去掉的 Entity
    @Override
    protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
        String identifier = name.toLowerCase(Locale.ROOT).replace("_entity", "");
        return new Identifier(identifier, quoted);
    }



}
