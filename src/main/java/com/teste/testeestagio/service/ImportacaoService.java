package com.teste.testeestagio.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

@Service
public class ImportacaoService {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(ImportacaoService.class.getName());
    private static final String INSERT_SQL = "INSERT INTO usuarios (id, name, email, phone) VALUES (?, ?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE name = VALUES(name), phone = VALUES(phone)"; // Para importar todos
    private static final int BATCH_SIZE = 10000;

    @PostConstruct
    @Transactional
    public void importarUsuarios() {
        try (Reader reader = new FileReader("C:/Users/H2061/Documents/users.csv");
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
             Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            conn.setAutoCommit(false);
            int count = 0;

            logger.info("Iniciando a importação do arquivo CSV...");

            for (CSVRecord record : csvParser) {
                String id = record.get("id");
                String name = record.get("name");
                String email = record.get("email");
                String phone = record.get("phone");

                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setString(4, phone);
                pstmt.addBatch();
                count++;

                if (count % BATCH_SIZE == 0) {
                    try {
                        pstmt.executeBatch(); // Executa o lote de inserções
                        conn.commit();
                    } catch (SQLException e) {
                        logger.severe("Erro ao executar batch durante a inserção na linha " + count + ": " + e.getMessage());
                        conn.rollback(); // Reverte a transação em caso de erro
                    }
                }
            }

            if (count % BATCH_SIZE != 0) {
                try {
                    pstmt.executeBatch(); // Executa o último lote de inserções
                    conn.commit();
                } catch (SQLException e) {
                    logger.severe("Erro ao executar batch final durante a inserção: " + e.getMessage());
                    conn.rollback();
                }
            }

            logger.info("Importação concluída com sucesso.");

        } catch (IOException e) {
            logger.severe("Erro de IO ao processar o arquivo CSV: " + e.getMessage());
        } catch (SQLException e) {
            logger.severe("Erro ao se conectar ao banco de dados: " + e.getMessage());
        }
    }
}
