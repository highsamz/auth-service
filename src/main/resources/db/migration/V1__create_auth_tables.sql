-- Tabela de roles
CREATE TABLE roles (
                       id UUID PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela de usuários
CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabela de relacionamento usuário x role
CREATE TABLE user_roles (
                            user_id UUID NOT NULL,
                            role_id UUID NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id)
                                REFERENCES users (id) ON DELETE CASCADE,
                            CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id)
                                REFERENCES roles (id) ON DELETE CASCADE
);

-- Inserção das roles padrão
INSERT INTO roles (id, name) VALUES
                                 (gen_random_uuid(), 'PASTOR'),
                                 (gen_random_uuid(), 'SECRETARIO'),
                                 (gen_random_uuid(), 'TESOUREIRO');
