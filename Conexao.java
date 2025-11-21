package com.mycompany.mavenproject1;

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

    public int selectNumeroAluno(String nm) {
        String sql = "select id_aluno from aluno\n"
                + "nome_aluno =? order by id_aluno desc limit 1";
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
    }//utilizado

    public String InsertAluno(String a) {
        String sql = "insert into aluno (nome_aluno) values(?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                return "Aluno adicionado com sucesso!";
            } else {
                return "Não foi possivel adicionar o aluno, verifique!";
            }

        } catch (SQLException e) {
            return "Erro ao adicionar aluno: " + e.getMessage();
        }
    }//utilizado

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
    }//utilizado

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
    }//utilizado

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
    }//utilizado

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
    }//utilizado

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
    }//utilizado

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

    }//utilizado

    public void CarrgarAlunos(List<Aluno> lista, int IDprova) {
        String sql = "select nome_aluno, aluno_id from notas \n"
                + "left join aluno on id_aluno=aluno_id\n"
                + "left join provas on prova_id=id_prova\n"
                + "where id_prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, IDprova);

            try (ResultSet rs = stmt.executeQuery()) {
                lista.clear();
                while (rs.next()) {
                    Aluno novoAln = null;
                    String nome = rs.getString("nome_aluno");
                    int id = rs.getInt("aluno_id");
                    novoAln = new Aluno(nome, id);
                    lista.add(novoAln);
                }

            }
        } catch (SQLException e) {
            escreve("Erro ao carregar Alunos");
        }

    }//utilizado

    public void CarrgarProvas(List<Provas> lista) {
        String sql = "select * from provas";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            lista.clear();
            while (rs.next()) {
                Provas NVprova = null;
                int id = rs.getInt("id_prova");
                String nome = rs.getString("prova");
                NVprova = new Provas(id, nome);
                lista.add(NVprova);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            escreve("Erro ao carregar provas");
        }
    }//utilizado

    public String InsertNotas(int prova, int aluno, double nota) {
        String sql = "update notas set nota=?\n"
                + "where aluno_id=? and prova_id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setDouble(1, nota);
            stmt.setInt(2, aluno);
            stmt.setInt(3, prova);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                return "Nota adicioanda com sucesso!!!";
            } else {
                return "Não foi possivel incluir nota Verifique!";
            }

        } catch (SQLException e) {
            return "Erro ao inserir Prova: " + e.getMessage();

        }
    }//utilizado

public boolean VinculoProvaAluno(int aluno) {
        //
        String sql = "select * from notas where aluno_id=?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, aluno);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return true;

                }

            }
        } catch (SQLException e) {
            escreve("Erro na verificação dos alunos: " + e.getMessage());
            return false;
        }
        return false;
    }//utilizado

    public void AlunosGeral(List<Aluno> lista) {
        String sql = "select * from aluno";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            lista.clear();
            while (rs.next()) {
                Aluno novoAln = null;
                String nome = rs.getString("nome_aluno");
                int id = rs.getInt("id_aluno");
                novoAln = new Aluno(nome, id);
                lista.add(novoAln);
            }

        } catch (SQLException e) {
            escreve("Erro ao carregar Alunos");
        }

    }//utilizado

public void excluiProva(int idprova) {
        String sql = "delete from provas where id_prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idprova);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("Prova excluida com sucesso!");
            } else {
                escreve("Não foi possivel excluir a prova!");
                return;
            }
        } catch (SQLException e) {
            escreve("Erro na exclusão da prova!: " + e.getMessage());
        }

    }//utilizado

  public void excluiAluno(int idaluno) {
        String sql = "delete from aluno where id_aluno=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idaluno);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("Aluno excuido com sucesso!");
            } else {
                escreve("Não foi possivel excluir o aluno!");
                return;
            }
        } catch (SQLException e) {
            escreve("Erro na exclusão do aluno!: " + e.getMessage());
        }

    }//utilizado

 public void nomeProva(String nome, int id) {
        String sql = "update provas set prova=? where id_prova=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("nome alterado com sucesso!");
            } else {
                escreve("não foi possivel alterar o nome da prova, verifique!");
            }
        } catch (SQLException e) {
            escreve("Erro ao alterar o nome da prova" + e.getMessage());
        }
    }//utilizado

    public void nomeAluno(String nome, int id) {
        String sql = "update aluno set nome_aluno=? where id_aluno=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                escreve("nome alterado com sucesso!");
            } else {
                escreve("não foi possivel alterar o nome do aluno, verifique!");
            }
        } catch (SQLException e) {
            escreve("Erro ao alterar o nome do aluno" + e.getMessage());
        }
    }//utilizado

    public double selectNota(int prova, int aluno) {
        String sql = "select nota from notas \n"
                + "left join aluno on id_aluno=aluno_id\n"
                + "left join provas on prova_id=id_prova\n"
                + "where id_prova=? and aluno_id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, prova);
            stmt.setInt(1, aluno);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return rs.getDouble("nota");
                }
            }
        } catch (SQLException e) {
            escreve("Erro ao consultar nota: " + e.getMessage());
            return 0;
        }

        return 0;
    }//utilizado
    /*
    public static void main(String[] args) {
        Conexao banco = new Conexao();
        List<Provas> aulas = new ArrayList<>();
        banco.CarrgarProvas(aulas);
        for (Provas p : aulas) {

            System.out.println("Prova:" + p.getProva());
        }
    }*/
}
