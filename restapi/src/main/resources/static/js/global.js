const SEQUENCE_KEY = "RAPA_API_EXP_APP_SEQUENCE";

function init() {
    window.localStorage.setItem(SEQUENCE_KEY, JSON.stringify(0));
}

function updateSequence(sequence) {
    window.localStorage.setItem(SEQUENCE_KEY, JSON.stringify(sequence));
}

function getSequence() {
    return parseInt(window.localStorage.getItem(SEQUENCE_KEY));
}

function validate() {

    if ( getSequence() === 0 ) {
        alert('게시글을 먼저 작성해주시기 바랍니다');
        return false;
    }

    return true;

}

function getPostValues() {
    return {
        title : document.getElementById('post-title').value,
        contents : document.getElementById('post-contents').value,
        author : document.getElementById('post-author').value
    }
}


function clearPostBody() {
    // Element Node 객체의 value는 input과 같은 입력받는 태그의 값을 변경할때 사용 :)
    document.getElementById('post-title').value = ''
    document.getElementById('post-contents').value = ''
    document.getElementById('post-author').value = ''
}

function refresh(title, contents, author) {

    if ( title != null ) {
        // innerText, innerHTML은 화면에 보이는 글자를 바꿀때 사용
        document.getElementById('title').innerText = title;
    }

    if ( author != null ) {
        document.getElementById('author').innerText = author;
    }

    if ( contents != null ) {
        document.getElementById('contents').innerText = contents;
    }

}

init();