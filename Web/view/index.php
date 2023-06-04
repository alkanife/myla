<?php
$type = 'blush';
if (!empty($_GET)) {
    $type = $_GET['type'];
}

$images_dir    = '../images/';
$gif_dirs = scandir($images_dir);

if (!in_array($type, $gif_dirs)) {
    $type = 'blush';
}

function getCount($typeName) {
    return iterator_count(new FilesystemIterator("../images/" . $typeName, FilesystemIterator::SKIP_DOTS));
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="og:title" content="Myla GIF Database"/>
    <meta property="og:type" content="website"/>
    <meta property="og:url" content="https://myla.alkanife.fr/view"/>
    <meta name="theme-color" content="#0f0f0f">
    <link rel="icon" href="https://myla.alkanife.fr/images/myla.png">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu+Mono&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    <title>Myla GIF Database - /<?php echo "$type"; ?></title>
</head>
<body>
    <div class="header">
        <h1>Myla GIF Database</h1>
        <p class="header_links"><a href="https://myla.alkanife.fr/images/">Index view</a> - <a href="https://github.com/alkanife/myla-website">Source (GitHub)</a> - <a href="https://myla.alkanife.fr/discord/">Add GIF (Discord server)</a></p>
        <div class="header_gifs">
            <?php
                foreach ($gif_dirs as $gif_dir) {
                    if (strpos($gif_dir, ".") === false) {
                        if (strcasecmp($gif_dir, $type) == 0) {
                            echo "<a class=\"type_link active_type_link\"";
                        } else {
                            echo "<a class=\"type_link\"";
                        }
                        echo " href=\"https://myla.alkanife.fr/view/?type=$gif_dir\">/$gif_dir</a>\n";
                    }
                }
            ?>
        </div>
    </div>
    <?php
        $count = getCount($type);
        echo "<div><p>/$type - $count</p></div>";

        if ($count > 5) {
            echo "<div class=\"gifs_container\">";
            for ($i=0; $i < $count; $i++) { 
                echo "<div class=\"gif_container\"><img src=\"https://myla.alkanife.fr/images/$type/$i.gif\" loading=\"lazy\"/></div>\n";
            }
        } else {
            echo "<div class=\"fixed_gifs_container\">";
            for ($i=0; $i < $count; $i++) { 
                echo "<div class=\"fixed_gif_container\"><img src=\"https://myla.alkanife.fr/images/$type/$i.gif\" loading=\"lazy\"/></div>\n";
            }
        }
        echo "</div>";
    ?>
</body>
</html>