INSERT INTO ranks (name, description)
VALUES
    ('Beginner', 'Novice level');
INSERT INTO credentials (email, password)
VALUES
    ('john.doe@example.com', 'cGFzc3dvcmQxMjMxOTkwLTAxLTAxVDAwOjAwWg==');
INSERT INTO users (nickname, description, avatar, total_experience, date_creating, ranks_id, credentials_id)
VALUES
    ('john_doe', 'Hello, I am John Doe.', 'https://example.com/avatar.png', 1000, '1990-01-01T00:00:00Z', null, 1);
INSERT INTO roles (name, description)
VALUES
    ('ROLE_ADMIN', 'Administrator role');
INSERT INTO post (content, users_id)
VALUES
    ('Hello, world!', null);
INSERT INTO category (name, description)
VALUES
    ('Technology', 'Category for technology-related topics');
INSERT INTO game (name)
VALUES
    ('Chess');
INSERT INTO achievement (name, condition, platform, experience, type, game_id)
VALUES
    ('First Win', 'Win your first game', 'PC', 100, 'General', null);
INSERT INTO achievement_request (game_name, achievement_name, platform, users_id)
VALUES
    ('Chess', 'Grandmaster', 'PC', null);
INSERT INTO comments (content, users_id, post_id, parent_comment_id)
VALUES
    ('Great post!', null, null, null);
INSERT INTO users_roles (user_id, roles_id)
VALUES
    (1, 1);
