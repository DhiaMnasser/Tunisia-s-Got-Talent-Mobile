<?php

namespace AchatBundle\Controller;

use AchatBundle\Entity\LigneCommande;
use AchatBundle\Entity\Panier;
use FOS\UserBundle\Model\User;
use StockBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;

/**
 * ApiLignecommande controller.
 *
 * @Route("Apilignecommande")
 */
class ApiLigneCommandeController extends Controller
{
    /**
     * Lists all ligneCommande entities.
     *
     * @Route("/index", name="api_lignecommande_index")
     * @Method("GET")
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("Proxies\\__CG__\\UserBundle\\Entity\\User")->find($request->get('user'));

        $panier = $em->getRepository("AchatBundle:Panier")->findByUser_Id($user);

        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findByPanier($panier);
//        dump($ligneCommandes);
//        exit();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($ligneCommandes);
        return new JsonResponse($formatted);

//        dump(new JsonResponse($formatted));
    }


    /**
     * Lists all ligneCommande entities.
     *
     * @Route("/getLigneCommandesByPanier", name="api_lignecommande_ByPanier")
     * @Method("GET")
     */
    public function getLigneCommandesByPanier(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findByPanier($request->get('panier'));
//        dump($ligneCommandes);
//        exit();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($ligneCommandes);
        return new JsonResponse($formatted);

//        dump(new JsonResponse($formatted));
    }
    /**
     * Creates a new ligneCommande entity.
     *
     * @Route("/new", name="api_lignecommande_new")
     * @Method({"GET", "POST"})

     */
    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $produit=$em->getRepository('StockBundle:Produit')->find($request->get('idProduit'));

        $user = $em->getRepository("Proxies\\__CG__\\UserBundle\\Entity\\User")->find($request->get('user'));


        $panier=$this->getDoctrine()->getManager()->getRepository('AchatBundle:Panier')->findByUser_Id($user);

        $ligneCommande = $this->getDoctrine()->getManager()->getRepository('AchatBundle:LigneCommande')->findByProduit($produit,$panier);


        if ($ligneCommande != null) {

            $ligneCommande->setQuantite(($ligneCommande->getQuantite()+1));
            $panier->setPrixtotal($panier->getPrixtotal() + $produit->getPrix());


        } else {
            $ligneCommande = new Lignecommande();
            $ligneCommande->setIdproduit($produit);
            $ligneCommande->setNomproduit($produit->getNom());
            $ligneCommande->setIdPanier($panier);
            $ligneCommande->setQuantite(1);

            $panier->setPrixtotal($panier->getPrixtotal() + ($produit->getPrix() * $ligneCommande->getQuantite()));
        }


            $em->persist($ligneCommande);
            $em->flush();

        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($ligneCommande);
        return new JsonResponse($formatted);


    }

    /**
     * Finds and displays a ligneCommande entity.
     *
     * @Route("/{id}/show", name="api_lignecommande_show")
     * @Method("GET")
     */
    public function showAction(Request $request)
    {


        $em = $this->getDoctrine()->getManager();
        $ligneCommande = $this->getDoctrine()->getManager()->getRepository(LigneCommande::class)->find($request->get('id'));
//        dump($ligneCommande);
//        exit();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($ligneCommande);
        return new JsonResponse($formatted);
    }



    /**
     * Displays a form to edit an existing ligneCommande entity.
     *
     * @Route("/{id}/edit", name="api_lignecommande_edit")
     * @Method({"GET", "POST"})
     */

    public function editAction(Request $request)
    {
//      recuperer la quantite avant la modification

        $em = $this->getDoctrine()->getManager();
        $ligneCommande = $this->getDoctrine()->getManager()->getRepository(LigneCommande::class)->find($request->get('id'));
        $qte = (integer)$request->get('qte');

        $panier=$this->getDoctrine()->getManager()->getRepository(Panier::class)->find($ligneCommande->getIdPanier());


        if ($ligneCommande->getQuantite() >= $qte) {
            $panier->setPrixtotal($panier->getPrixtotal() - ($ligneCommande->getQuantite() - $qte) * ($ligneCommande->getIdproduit()->getPrix()));
        } else {
            $panier->setPrixtotal($panier->getPrixtotal() + ($qte - $ligneCommande->getQuantite()) * ($ligneCommande->getIdproduit()->getPrix()));
        }
        dump($qte,$ligneCommande);
//        exit();
        $ligneCommande->setQuantite($qte);
        $em->flush();

        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($ligneCommande);
        return new JsonResponse($formatted);


    }

    /**
     * Deletes a ligneCommande entity.
     *
     * @Route("/{id}/delete", name="api_lignecommande_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, LigneCommande $ligneCommande)
    {
        $form = $this->createDeleteForm($ligneCommande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($ligneCommande);
            $em->flush();
        }

        return $this->redirectToRoute('lignecommande_index');
    }

    /**
     * Creates a form to delete a ligneCommande entity.
     *
     * @param LigneCommande $ligneCommande The ligneCommande entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(LigneCommande $ligneCommande)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('lignecommande_delete', array('id' => $ligneCommande->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

    /**
     * Deletes a ligneCommande entity.
     *
     * @Route("/{id}/supprimer", name="api_lignecommande_supprimer")
     * @Method("DELETE")
     */

    public function supprimerAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $lignecommande = $em->getRepository("AchatBundle:LigneCommande")->find($request->get('id'));
//dump($lignecommande);
//exit();
        $produit=$this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->find($lignecommande->getIdproduit());

        $panier=$this->getDoctrine()->getManager()->getRepository(Panier::class)->find($lignecommande->getIdPanier());
        $panier->setPrixtotal($panier->getPrixtotal() - ($produit->getPrix()*$lignecommande->getQuantite()));

        $em->remove($lignecommande);
        $em->flush();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($lignecommande);
        return new JsonResponse($formatted);
    }
}
