
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Conexao extends Fclt {

    private static final String URL = "jdbc:postgresql://localhost:5432/Teste";
    private static final String USER = "postgres";
    private static final String PASSWORD = "09042003";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            escreve("Driver não encontrado!");
        } catch (SQLException e) {
            escreve("Erro ao conectar ao Banco!" + e.getMessage());
        }
        return null;
    }

    public boolean ConfereAlunos() {
        //
        String sql = "SELECT * FROM aluno";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return true;
                /*
                escreve("ID: " + rs.getInt("id_aluno"));
                escreve("Nome: " + rs.getString("nome_aluno"));
                escreve("-------------------------");*/
            }

        } catch (SQLException e) {
            escreve("Erro ao listar alunos: " + e.getMessage());
            return false;
        }
        return false;
    }//ok

    public void ListarAlunos() {
        //
        String sql = "SELECT * FROM aluno";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                escreve("ID: " + rs.getInt("id_aluno"));
                escreve("Nome: " + rs.getString("nome_aluno"));
                escreve("-------------------------");
            }

        } catch (SQLException e) {
            escreve("Erro ao listar alunos: " + e.getMessage());

        }
    }//ok

    public int selectNumeroAluno(String nm) {
        String sql = "select id_aluno from aluno\n"
                + "where lower(nome_aluno) =?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, nm);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("id_aluno");
                }
            }
        } catch (SQLException e) {
            escreve("Erro ao consultar aluno: " + e.getMessage());
            return 0;
        }

        return 0;
    }//ok

    public String selectNomeAluno(int nm) {
        String sql = "select nome_aluno from aluno\n"
                + "where id_aluno=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, nm);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return rs.getString("nome_aluno");
                }
            }
        } catch (SQLException e) {
            escreve("Erro ao consultar aluno: " + e.getMessage());
            return null;
        }

        return null;
    }//ok

    public void CarrgarAlunos(List<Aluno> lista) {
        String sql = "select * from aluno";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            lista.clear();
            while (rs.next()) {
                Aluno NVAluno = null;
                String nome = rs.getString("nome_aluno");
                int id = rs.getInt("id_aluno");
                NVAluno = new Aluno(nome, id);
                lista.add(NVAluno);
            }

        } catch (SQLException e) {
            escreve("Erro ao carregar Alunos");
        }
    }//ok

    public void InsertAluno(String a) {
        String sql = "insert into aluno (nome_aluno) values(?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("Aluno adicionado com sucesso!(InsertAluno)");
            } else {
                escreve("Não foi possivel adicionar o aluno, verifique!");
            }

        } catch (SQLException e) {
            escreve("Erro ao adicionar aluno: " + e.getMessage());
        }
    }//ok

    public void InsertProva(String p) {
        String sql = "insert into provas (prova) values(?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, p);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("Prova inserida com sucesso!");
            } else {
                escreve("Não foi possivel incluir a prova Verifique!");
            }

        } catch (SQLException e) {
            escreve("Erro ao inserir Prova: " + e.getMessage());

        }
    }//ok

    public String selectNomeprova(int nm) {
        String sql = "select prova from provas where id_prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, nm);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return rs.getString("prova");
                }
            }
        } catch (SQLException e) {
            escreve("Erro ao consultar prova: " + e.getMessage());
            return null;
        }

        return null;
    }//ok

    public int selectIDprova(String nm) {
        String sql = "select id_prova from provas where prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, nm);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("id_prova");
                }
            }
        } catch (SQLException e) {
            escreve("Erro ao consultar prova: " + e.getMessage());
            return 0;
        }

        return 0;
    }//ok

    public void Listarprovas() {
        //
        String sql = "SELECT * FROM provas";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            escreve("-------------------------");
            while (rs.next()) {
                escreve("ID: " + rs.getInt("id_prova"));
                escreve("Nome: " + rs.getString("prova"));
                escreve("-------------------------");
            }

        } catch (SQLException e) {
            escreve("Erro ao listar provas: " + e.getMessage());

        }
    }

    public void CarrgarProvas(List<Notas> lista) {
        String sql = "select * from provas";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            lista.clear();
            while (rs.next()) {
                Notas NVprova = null;
                int id = rs.getInt("id_prova");
                NVprova = new Notas(id);
                lista.add(NVprova);
            }

        } catch (SQLException e) {
            escreve("Erro ao carregar Alunos");
        }
    }

    public void ListaNotas(int prova) {
        String sql = "select nome_aluno, nota, prova from notas\n"
                + "left join aluno on id_aluno =aluno_id\n"
                + "left join provas on id_prova=prova_id\n"
                + "where id_prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, prova);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    escreve("Aluno: " + rs.getString("nome_aluno"));
                    escreve("Nota: " + rs.getString("nota"));
                }
            }
        } catch (SQLException e) {
            escreve("Erro ao consultar notas: " + e.getMessage());
        }

    }

    public void InsertNotas(int prova, int aluno, double nota) {
        String sql = "update notas set nota=?\n"
                + "where aluno_id=? and prova_id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setDouble(1, nota);
            stmt.setInt(2, aluno);
            stmt.setInt(3, prova);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("Adicionando nota...");
            } else {
                escreve("Não foi possivel incluir nota Verifique!");
            }

        } catch (SQLException e) {
            escreve("Erro ao inserir Prova: " + e.getMessage());

        }
    }

    public void ALunosProva(List<Aluno> lista, int idProva) {
        String sql = "select id_aluno, nome_aluno from notas\n"
                + "left join aluno on id_aluno=aluno_id\n"
                + "left join provas on prova_id=id_prova\n"
                + "where id_prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idProva);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno novoAluno = null;
                    String nome = rs.getString("nome_aluno");
                    int id = rs.getInt("id_aluno");
                    novoAluno = new Aluno(nome, id);
                    lista.add(novoAluno);
                }

            }
        } catch (SQLException e) {
            escreve("Erro ao buscar Alunos: " + e.getMessage());
        }
    }

    public void vinculaAluno(int aluno, int prova) {
        String sql = "insert into notas (aluno_id, prova_id)\n"
                + "values (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, aluno);
            stmt.setInt(2, prova);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("Aluno vinculado á prova!");
            } else {
                escreve("Não foi possivel realizar o vinculo, verifique!");
            }

        } catch (SQLException e) {
            escreve("Erro ao vincular aluno: " + e.getMessage());

        }
    }
//    public static void main(String[] args) {
//        Conexao teste = new Conexao();
//        ArrayList<Aluno> arrayteste = new ArrayList<>();
//        //teste.InsertAluno("Ellen");//ok
//        //escreve("o nome do Aluno com id 2 é " + teste.selectNomeAluno(2));
//        /*boolean testar = teste.ConfereAlunos();
//        if (testar == true) {
//            escreve("existem alunos");
//        } else {
//            escreve("não existem alunos");
//        }*/
// /*teste.CarrgarAlunos(arrayteste);
//        for (int i = 0; i < arrayteste.size(); i++) {
//            arrayteste.get(i).apresentar();
//        }*/
//    //teste.InsertProva("matemática");
//    //teste.Listarprovas();
//    //teste.InsertNotas(1, 2, 5);
//    //teste.ListaNotas(1);
//}

}
