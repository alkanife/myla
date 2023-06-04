<?php 

header("Content-Type: application/json; charset=UTF-8");

function getCount($typeName) {
    return iterator_count(new FilesystemIterator(__DIR__ . "/images/" . $typeName, FilesystemIterator::SKIP_DOTS));
}

$obj = new stdClass();
$obj->blush = getCount("blush");
$obj->cookie = getCount("cookie");
$obj->cry = getCount("cry");
$obj->headpat = getCount("headpat");
$obj->hehe = getCount("hehe");
$obj->hi = getCount("hi");
$obj->hug = getCount("hug");
$obj->idk = getCount("idk");
$obj->kiss = getCount("kiss");
$obj->laugh = getCount("laugh");
$obj->meme = getCount("meme");
$obj->notlikethis = getCount("notlikethis");
$obj->party = getCount("party");
$obj->pout = getCount("pout");
$obj->punch = getCount("punch");
$obj->slap = getCount("slap");
$obj->smile = getCount("smile");
$obj->wink = getCount("wink");
$obj->pray = getCount("pray");
$obj->hide = getCount("hide");

$json = json_encode($obj);

echo $json;

?>