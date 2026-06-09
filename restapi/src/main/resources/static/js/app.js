function testCall(){
    console.log("hello world!");
}

//데이터 불러오기
function createPost(){
    const data = getPostValues();
    //console.log(data);

    fetch('http://localhost:8080/posts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) //문자열 JSON으로 변경
    }).then(resp =>
        resp.json()
    ).then(data => {
        console.log(data);
    }).catch(onRejected => {
        console.error('Error posting data:', onRejected);
    })
}