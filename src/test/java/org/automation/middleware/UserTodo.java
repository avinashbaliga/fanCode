package org.automation.middleware;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.automation.pojos.allTodos.AllTodosItem;
import org.automation.pojos.allUsers.AllUsersItem;
import org.automation.utilities.CommonRestUtils;
import org.automation.utilities.TestConfig;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class UserTodo extends CommonRestUtils {

    private AllTodosItem[] allTodos = null;
    private List<Integer> fanCodeUserIds = null;

    public UserTodo() {
        super(TestConfig.get("baseUri"));
    }

    private void getAllTodoTasks() {
        Response allTodoTasks = request(TestConfig.get("todosBasePath"), Method.GET);
        allTodos = (AllTodosItem[]) mapJsonToPojo(allTodoTasks.getBody().asString(), AllTodosItem[].class);
    }

    public void verifyUsersHaveTodoTasks() {
        getAllTodoTasks();
        Assert.assertTrue(allTodos.length > 0, "No Todo items found");
    }

    public void getUsersBelongingToCityFanCode() {
        fanCodeUserIds = getFanCodeCityUserIds();
    }

    private List<Integer> getFanCodeCityUserIds() {
        int lat, lng;
        List<Integer> fanCodeUserIds = new ArrayList<>();
        Response allUsersResponse = request(TestConfig.get("usersBasePath"), Method.GET);
        AllUsersItem[] allUsers = (AllUsersItem[]) mapJsonToPojo(allUsersResponse.getBody().asString(), AllUsersItem[].class);

        for (AllUsersItem usersItem : allUsers) {
            lat = getLat(usersItem);
            lng = getLong(usersItem);

            if ((lat >= -40 && lat <= 5) && (lng >= 5 && lng <= 100))
                fanCodeUserIds.add(getUserId(usersItem));
        }

        return fanCodeUserIds;
    }

    private int getLat(AllUsersItem usersItem) {
        String lat = usersItem.address.geo.lat;
        return Integer.parseInt(lat.substring(0, lat.indexOf('.')));
    }

    private int getLong(AllUsersItem usersItem) {
        String lng = usersItem.address.geo.lng;
        return Integer.parseInt(lng.substring(0, lng.indexOf('.')));
    }

    private int getUserId(AllUsersItem usersItem) {
        return usersItem.id;
    }

    public void verifyCompletionPercentage(int expectedCompletionPercentage) {
        List<AllTodosItem> fanCodeUserTodoItems = getTodoOfFanCodeCityUsers();
        calculateAndVerifyCompletionPercentage(fanCodeUserTodoItems, expectedCompletionPercentage);
    }

    private List<AllTodosItem> getTodoOfFanCodeCityUsers() {
        int todoUserId;
        List<AllTodosItem> fanCodeUserTodoItems = new ArrayList<>();

        for (AllTodosItem todosItem : allTodos) {
            todoUserId = todosItem.userId;

            if (fanCodeUserIds.contains(todoUserId)) {
                fanCodeUserTodoItems.add(todosItem);
            }
        }

        return fanCodeUserTodoItems;
    }

    private void calculateAndVerifyCompletionPercentage(List<AllTodosItem> fanCodeUserTodoItems, int expectedCompletionPercentage) {
        int completedCount = 0;
        int pendingCount = 0;
        int userId = 0;
        int completionPercentage;

        for (int fanCodeUserId : fanCodeUserIds) {
            for (AllTodosItem item : fanCodeUserTodoItems) {
                if ((userId = item.userId) == fanCodeUserId) {
                    if (item.completed)
                        completedCount++;
                    else pendingCount++;
                }
            }
            completionPercentage = getPercentage(completedCount, pendingCount);
            Assert.assertTrue(completionPercentage > expectedCompletionPercentage,
                    "The todo completion percentage of user with userId '" + userId + "' is '" + completionPercentage + "' which is less than the minimum threshold of " + expectedCompletionPercentage + "%. Failing the test case.");
        }
    }

    private int getPercentage(int completedTodoCount, int pendingTodoCount) {
        return ((100 / (completedTodoCount + pendingTodoCount)) * completedTodoCount);
    }
}
