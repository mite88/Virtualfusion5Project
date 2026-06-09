function getPostValues(){
   /* const postTitleEl = document.getElementById("post-title");
    const postAuthorEl = document.getElementById("post-contents");
    const postContentsEl = document.getElementById("post-author");

    const title = postTitleEl.value;
    const author = postAuthorEl.value;
    const contents = postContentsEl.value;*/

    //console.log(title);

    return{
        title :  document.getElementById("post-title").value,
        author : document.getElementById("post-author").value,
        contents : document.getElementById("post-contents").value
    }


}