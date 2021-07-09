$(document).ready(function () {
    console.log('working');
    const modal = $(this)
    $('.table.editButton').on('click', function (event) {
        $('editModal').modal('toggle');
    })
})

$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Кнопка, что спровоцировало модальное окно
    var recipient = button.data('whatever') // Извлечение информации из данных-* атрибутов
    // Если необходимо, вы могли бы начать здесь AJAX-запрос (и выполните обновление в обратного вызова).
    // Обновление модальное окно Контента. Мы будем использовать jQuery здесь, но вместо него можно использовать привязки данных библиотеки или других методов.
    const modal = $(this)
    modal.find('.modal-title').text('New message to ' + recipient)
    modal.find('.modal-body input').val(recipient)
})