# Ws Reto - Leer las instrucciones para levantar el proyecto
#Creamos el data source bajo un EAP 7.3

<xa-datasource jndi-name="java:/jboss/settingsDS" pool-name="settingsDS" enabled="true" use-java-context="true" use-ccm="true">
                    <xa-datasource-property name="ServerName">
                        192.168.0.1
                    </xa-datasource-property>
                    <xa-datasource-property name="PortNumber">
                        5432
                    </xa-datasource-property>
                    <xa-datasource-property name="DatabaseName">
                        configuration
                    </xa-datasource-property>
                    <xa-datasource-property name="User">
                        postgres
                    </xa-datasource-property>
                    <xa-datasource-property name="Password">
                        postgres123
                    </xa-datasource-property>
                    <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
                    <driver>postgresqlxa</driver>
                    <xa-pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>50</max-pool-size>
                        <prefill>false</prefill>
                        <use-strict-min>false</use-strict-min>
                        <flush-strategy>FailingConnectionOnly</flush-strategy>
                        <no-tx-separate-pools>true</no-tx-separate-pools>
                        <pad-xid>false</pad-xid>
                        <wrap-xa-resource>true</wrap-xa-resource>
                    </xa-pool>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                    </validation>
                    <statement>
                        <prepared-statement-cache-size>100</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </xa-datasource>

 # Creamos el driver como modulo
 
 <drivers>
   <driver name="postgresqlxa" module="org.postgresql">
     <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
   </driver>
 </drivers>
 
 
 # Creamos las tablas en el esquema "configuration" de base de datos PostgreSql
 
 
CREATE TABLE rol (
  id_rol serial NOT NULL, 
  descripcion character varying(50), 
  CONSTRAINT id_rol_pk PRIMARY KEY (id_rol)
);

CREATE TABLE persona (
  id_persona serial NOT NULL, 
  cedula character varying(10) not null, 
  nombres character varying(50) not null, 
  apellidos character varying(50) not null, 
  email character varying(30) not null, 
  usuario character varying(20), 
  clave character varying(100), 
  fecha_nacimiento date,
  direccion character varying(80),  
  telefono character varying(10), 
  vacunado character(1),
  id_rol integer,
  CONSTRAINT id_persona_pk PRIMARY KEY (id_persona),
  CONSTRAINT id_rol_fk FOREIGN KEY (id_rol)
      REFERENCES rol (id_rol) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE persona_vacuna (
  id_vacuna serial NOT NULL, 
  tipo_vacuna character varying(15) not null, 
  fecha_vacuna timestamp without time zone NOT NULL,
  numero_dosis integer not null,
  id_persona integer not null,
  CONSTRAINT id_vacuna_pk PRIMARY KEY (id_vacuna),
  CONSTRAINT id_persona_fk FOREIGN KEY (id_persona)
      REFERENCES persona (id_persona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

select * from rol;
select * from persona;
select * from persona_vacuna;

insert into rol values (1, 'ADMINISTRADOR');
insert into rol values (2, 'EMPLEADO');
insert into persona values (1, '1716277870', 'Juan José', 'Rea', 'juan.rea@gmail.com', 'juan.rea', '123456', '1983-03-04', 'Luis Andrade Oe1-631 Vicente Duque', '0987029500', '0', 1);


# Compilamos el proyecto y agregamos el war generado

reto-wmpv-ws-service/target/reto-wmpv-ws-service.war

#Verificamos el servicio

curl --request POST \
  --url http://localhost:8080/reto-wmpv-ws-service/rest/wmpv/login \
  --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ0b2tlbkp3dFdpbGxpYW1QYXRpbm8iLCJzdWIiOiJhZG1pbmlzdHJhZG9yfEFETUlOIiwiYXV0aG9yaXRpZXMiOlsiQURNSU5JU1RSQURPUiJdLCJpYXQiOjE2NjA5NzQxOTd9.3pz-30Z3ZF8rM7iuE9rbekl7zNJb4l34k_EgxhPf1GnxJGraNN3II-fVLN45ZrKe0OCwlGnofFaNoi4vsPVGkQ' \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=0QMdfk3PQu5D9iyjPeMuZnejHSw8T6ZrAk4rW75r.uio-sistem-021 \
  --data '{
	"user":"juan.rea",
	"pass":"123456",
	"keepSession": "true"
}'