<#import "parts/common.ftlh" as c>

<@c.page "Результат теста">
    <div class="text-center">
        <h1 style="margin-top: 100px">${test.getTestName()}</h1>
        <div style="margin-top: 100px"><h1>Ваш результат: </h1></div>
        <div style="margin-top: 100px"><input type="button" class="startButton" value="Вернуться к тестам" onClick='location.href="/main"'></div>
    </div>
</@c.page>