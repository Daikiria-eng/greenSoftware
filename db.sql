/*CREATE TABLE users(
    user_uuid UUID NOT NULL UNIQUE,
    userName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    user_level INT,
    PRIMARY KEY (user_uuid)
);

CREATE TABLE modules(
    module_name VARCHAR(20) NOT NULL,
    description VARCHAR(250) NOT NULL,
    content VARCHAR NOT NULL,
    exam_content json NOT NULL,
    PRIMARY KEY (module_name)
);

CREATE TABLE module_content(
    module_name VARCHAR(20) NOT NULL,
    content_text VARCHAR NOT NULL,
    example_lang VARCHAR NOT NULL,
    example VARCHAR NOT NULL,
    content_order INT NOT NULL,
    CONSTRAINT pk_module_content
    PRIMARY KEY (module_name),
    CONSTRAINT fk_module_name_modules
    FOREIGN KEY (module_name)
    REFERENCES modules(module_name)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_stats(
    user_uuid UUID NOT NULL,
    module_name VARCHAR(20) NOT NULL,
    module_progress INT NOT NULL,
    module_attempts INT NOT NULL,
    module_attempt_achieve BOOLEAN NOT NULL,
    activity_date DATE NOT NULL,
    PRIMARY KEY (user_uuid),
    CONSTRAINT user_stat_id
    FOREIGN KEY (user_uuid)
    REFERENCES users(user_uuid)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_module_name_stat
    FOREIGN KEY (module_name)
    REFERENCES modules(module_name)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE UNIQUE INDEX email ON users(email);
CREATE UNIQUE INDEX module_name ON modules(module_name);*/


/*CREATE OR REPLACE FUNCTION insert_user(uuuid UUID,uname TEXT, uemail TEXT,passwd TEXT)
RETURNS INT AS $$
DECLARE inserted INT;
BEGIN
    INSERT INTO users (user_uuid,user_name,email,password)
    VALUES (uuuid,uname,uemail,passwd);
    GET DIAGNOSTICS inserted := ROW_COUNT;
    RETURN inserted;
END;
$$ LANGUAGE plpgsql*/

/*CREATE OR REPLACE FUNCTION validate_user(uemail TEXT,passwd TEXT)
RETURNS TABLE(
    uname VARCHAR,
    ulevel INT
) AS $$
BEGIN
    RETURN QUERY SELECT 
        user_name,user_level FROM users 
        WHERE email=uemail AND password=passwd;
END;
$$ LANGUAGE plpgsql*/

/*CREATE OR REPLACE FUNCTION get_questions(m_name TEXT)
RETURNS TABLE (exam_json VARCHAR) AS $$
BEGIN
    RETURN QUERY SELECT exam_content::VARCHAR
        FROM modules WHERE module_name=m_name;
END;
$$ LANGUAGE plpgsql*/

CREATE OR REPLACE FUNCTION get_content(IN m_name TEXT,OUT o_content_text VARCHAR, OUT o_example_lang VARCHAR,OUT o_example VARCHAR, OUT o_content_order INT)
AS $$
BEGIN
    SELECT 
        content_text,example_lang,example,content_order
    INTO
        o_content_text,o_example_lang,o_example,o_content_order
    FROM module_content
    WHERE module_name=m_name;
END;
$$ LANGUAGE plpgsql

