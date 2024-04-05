package org.example;

import org.example.controller.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        UserController userController = context.getBean(UserController.class);
        RoleController roleController = context.getBean(RoleController.class);
        RankController rankController = context.getBean(RankController.class);
        PostController postController = context.getBean(PostController.class);
        GameController gameController = context.getBean(GameController.class);
        CommentController commentController = context.getBean(CommentController.class);
        CategoryController categoryController = context.getBean(CategoryController.class);
        AchievementRequestController achievementRequestController = context.getBean(AchievementRequestController.class);
        AchievementController achievementController = context.getBean(AchievementController.class);
        String serializedUser1 = """
                {
                    "id":1,
                    "nickname":"Вася",
                    "description":"пожилой",
                    "avatar":"ссылка",
                    "totalExp":1000000
                }
                """;
        String serializedUser2 = """
                {
                    "id":2,
                    "nickname":"Вася",
                    "description":"пожилой",
                    "avatar":"ссылка",
                    "totalExp":1000000
                }
                """;
        String serializedUserUpdate = """
                {
                    "id":1,
                    "nickname":"Ваня",
                    "description":"молодой",
                    "avatar":"ссылка",
                    "totalExp":10
                }
                """;
        userController.createUser(serializedUser1);
        userController.getById(1);
        userController.createUser(serializedUser2);
        userController.getById(2);
        userController.deleteById(2);
        String serializedRole1 = """
                {
                "id":1,
                "name":"пользователь",
                "description":"стандартный"
                }
                """;
        String serializedRole2 = """
                {
                "id":2,
                "name":"пользователь",
                "description":"стандартный"
                }
                """;
        String serializedRoleUpdate = """
                {
                "id":1,
                "name":"пользователь",
                "description":"нестандартный"
                }
                """;
        roleController.createRole(serializedRole1);
        roleController.createRole(serializedRole2);
        roleController.getById(1);
        roleController.getById(2);
        roleController.deleteById(2);
        roleController.updateRole(serializedRoleUpdate);
        String serializedRank1 = """
                {
                "id":1,
                "name":"hdh",
                "description":"пожилой"
                }
                """;
        String serializedRank2 = """
                {
                "id":2,
                "name":"hdh",
                "description":"пожилой"
                }
                """;
        String serializedRankUpdate = """
                {
                "id":1,
                "name":"hdh",
                "description":"непожилой"
                }
                """;
        rankController.createRank(serializedRank1);
        rankController.createRank(serializedRank2);
        rankController.getById(1);
        rankController.getById(2);
        rankController.deleteById(2);
        rankController.updateRank(serializedRankUpdate);
        String serializedPost1 = """
                {
                "id":1,
                "value":"дарова"
                }
                """;
        String serializedPost2 = """
                {
                "id":2,
                "value":"дарова"
                }
                """;
        String serializedPostUpdate = """
                {
                "id":1,
                "value":"дарова bro"
                }
                """;
        postController.createPost(serializedPost1);
        postController.createPost(serializedPost2);
        postController.getById(1);
        postController.getById(2);
        postController.deleteById(2);
        postController.updatePost(serializedPostUpdate);
        String serializedCategory1 = """
                {
                "id":1,
                "name":"Игры",
                "description":"Игры"
                }
                """;
        String serializedCategory2 = """
                {
                "id":2,
                "name":"Игры",
                "description":"Игры"
                }
                """;
        String serializedCategoryUpdate = """
                {
                "id":1,
                "name":"Игры",
                "description":"Игры"
                }
                """;
        categoryController.createCategory(serializedCategory1);
        categoryController.createCategory(serializedCategory2);
        categoryController.getById(1);
        categoryController.getById(2);
        categoryController.deleteById(2);
        categoryController.updateCategory(serializedCategoryUpdate);
        String serializedComment1 = """
                {
                "id":1,
                "value":"круть"
                }
                """;
        String serializedComment2 = """
                {
                "id":2,
                "value":"круть"
                }
                """;
        String serializedCommentUpdate = """
                {
                "id":1,
                "value":"круть cool"
                }
                """;
        commentController.createComment(serializedComment1);
        commentController.createComment(serializedComment2);
        commentController.getById(1);
        commentController.getById(2);
        commentController.createComment(serializedCommentUpdate);
        String serializedGame1 = """
                {
                "id":1,
                "name":"Шахматы"
                }
                """;
        String serializedGame2 = """
                {
                "id":2,
                "name":"Шашки"
                }
                """;
        String serializedGameUpdate = """
                {
                "id":1,
                "name":"Шахматы2"
                }
                """;
        gameController.createGame(serializedGame1);
        gameController.createGame(serializedGame2);
        gameController.getById(1);
        gameController.getById(2);
        gameController.deleteById(2);
        gameController.updateGame(serializedGameUpdate);
        String serializedAchievementRequest1 = """
                {
                    "id":1,
                    "gameName":"Шахматы",
                    "achievementName":"ачивка",
                    "platform":"платформа"
                }
                """;
        String serializedAchievementRequest2 = """
                {
                    "id":2,
                    "gameName":"Шахматы",
                    "achievementName":"ачивка",
                    "platform":"платформа"
                }
                """;
        String serializedAchievementRequestUpdate = """
                {
                    "id":1,
                    "gameName":"Шахматы",
                    "achievementName":"ачивка",
                    "platform":"крутая платформа"
                }
                """;
        achievementRequestController.createAchievementRequest(serializedAchievementRequest1);
        achievementRequestController.createAchievementRequest(serializedAchievementRequest2);
        achievementRequestController.getById(1);
        achievementRequestController.getById(2);
        achievementRequestController.deleteById(2);
        achievementRequestController.updateAchievementRequest(serializedAchievementRequestUpdate);
        String serializedAchievement1 = """
                {
                    "id":1,
                    "name":"ачивка",
                    "condition":"быть крутым",
                    "platform":"платформа",
                    "achievementExperience":3,
                    "type":"неигровая"
                }
                """;
        String serializedAchievement2 = """
                {
                    "id":2,
                    "name":"ачивка",
                    "condition":"быть крутым",
                    "platform":"платформа",
                    "achievementExperience":3,
                    "type":"неигровая"
                }
                """;
        String serializedAchievementUpdate = """
                {
                    "id":1,
                    "name":"ачивка",
                    "condition":"быть крутым",
                    "platform":"платформа",
                    "achievementExperience":3,
                    "type":"неигровая"
                }
                """;
        achievementController.createAchievement(serializedAchievement1);
        achievementController.createAchievement(serializedAchievement2);
        achievementController.getById(1);
        achievementController.getById(2);
        achievementController.deleteById(2);
        achievementController.updateAchievement(serializedAchievementUpdate);
    }
}
